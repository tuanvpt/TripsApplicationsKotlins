package com.example.tripsapplicationskotlins.ui.update

import android.Manifest
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.View.GONE
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelStoreOwner
import com.example.tripsapplicationskotlins.R
import com.example.tripsapplicationskotlins.base.BaseFragment
import com.example.tripsapplicationskotlins.databinding.FragmentUpdateBinding
import com.example.tripsapplicationskotlins.utils.LogUtil
import com.example.tripsapplicationskotlins.utils.exts.hide
import com.example.tripsapplicationskotlins.utils.exts.show
import dagger.hilt.android.AndroidEntryPoint
import io.agora.rtc2.*
import io.agora.rtc2.video.VideoCanvas


@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding, UpdateViewModel>() {

    private val PERMISSION_REQ_ID = 22

    // Join room
    // https://console.agora.io/invite?sign=6aabc05e1de7abbc3e06d44e81f2d101%3A11b8e824e645b7920c0242b59c43601705b1572f9b2361773b454da111d2e18d

    // Fill the App ID of your project generated on Agora Console.
    private val appId = "f9ec765fb7aa4db48616c3a9e3418912"

    // Fill the channel name.
    private val channelName = "papaycoders"

    // Fill the temp token generated on Agora Console.
    private val token =
        "007eJxTYChsf5DZGqsuX/0kyHbbARfvuxlLjqkc4dt48LGC65KUFD0FhjTL1GRzM9O0JPPERJOUJBMLM0OzZONEy1RjE0MLS0MjVb83yQ2BjAyPEyawMjJAIIjPzVCQWJBYmZyfklpUzMAAADTZIkw="

    // An integer that identifies the local user.
    private val uid = 0
    private var isJoined = false

    private var agoraEngine: RtcEngine? = null

    //SurfaceView to render local video in a Container.
    private var localSurfaceView: SurfaceView? = null

    //SurfaceView to render Remote video in a Container.
    private var remoteSurfaceView: SurfaceView? = null

    private val REQUESTED_PERMISSIONS = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA
    )

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentUpdateBinding.inflate(inflater)

    override fun setUpView() {
        if (!checkSelfPermission()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUESTED_PERMISSIONS,
                PERMISSION_REQ_ID
            );
        }
        setupVideoSDKEngine()


        viewBinding.btnJoin.setOnClickListener {
            joinCall();
        }
        viewBinding.btnLeave.setOnClickListener {
            leaveChannel();
        }
        viewBinding.iBtnCam.setOnClickListener {
            agoraEngine!!.switchCamera()
        }
        viewBinding.iBtnVideo.setOnClickListener {
            agoraEngine!!.enableVideo()
            localSurfaceView = RtcEngine.CreateRendererView(requireContext())
        }


        //Audio
        if (viewBinding.iBtnAudio.isSelected) {
            viewBinding.iBtnAudio.isSelected = false
            viewBinding.iBtnAudio.setImageResource(R.drawable.ic_mic_on)

        } else {
            viewBinding.iBtnAudio.isSelected = true
            viewBinding.iBtnAudio.setImageResource(R.drawable.ic_mic_off)

        }
        agoraEngine!!.muteLocalAudioStream(viewBinding.iBtnAudio.isSelected);
    }

    private fun leaveChannel() {
        if (!isJoined) {
            showToast("Join a channel first")
        } else {
            agoraEngine!!.leaveChannel()
            showToast("\"Hello everyone, I wanted to let you know that I'll be leaving this channel. I've appreciated the conversations and connections we've made here, and I hope we can stay in touch in other ways. Best of luck to you all!");
            if (remoteSurfaceView != null) remoteSurfaceView!!.hide()
            if (localSurfaceView != null) localSurfaceView!!.hide()
        }
    }


    private fun joinCall() {
        if (checkSelfPermission()) {
            val options = ChannelMediaOptions()
            // For a Video call, set the channel profile as COMMUNICATION.
            options.channelProfile = Constants.CHANNEL_PROFILE_COMMUNICATION
            // Set the client role as BROADCASTER or AUDIENCE according to the scenario.
            options.clientRoleType = Constants.CLIENT_ROLE_BROADCASTER
            // Display LocalSurfaceView.
            setupLocalVideo()
            localSurfaceView!!.show()
            // Start local preview.
            agoraEngine!!.startPreview()
            // Join the channel with a temp token.
            // You need to specify the user ID yourself, and ensure that it is unique in the channel.
            agoraEngine!!.joinChannel(token, channelName, uid, options)
        } else {
            showToast("Permissions was not granted")
        }
    }


    private fun setupVideoSDKEngine() {
        try {
            val config = RtcEngineConfig()
            config.mContext = requireContext()
            config.mAppId = appId
            config.mEventHandler = mRtcEventHandler
            agoraEngine = RtcEngine.create(config)
            // By default, the video module is disabled, call enableVideo to enable it.
            agoraEngine!!.enableVideo()
        } catch (e: Exception) {
            LogUtil.e(e.toString())
        }
    }

    private fun checkSelfPermission(): Boolean {
        return !(ContextCompat.checkSelfPermission(
            requireContext(),
            REQUESTED_PERMISSIONS[0]
        ) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    REQUESTED_PERMISSIONS[1]
                ) != PackageManager.PERMISSION_GRANTED)
    }

    private val mRtcEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        // Listen for the remote host joining the channel to get the uid of the host.
        override fun onUserJoined(uid: Int, elapsed: Int) {
            LogUtil.e("Remote user joined $uid")
            // Set the remote video view
            activity?.runOnUiThread { setupRemoteVideo(uid) }
        }

        override fun onJoinChannelSuccess(channel: String, uid: Int, elapsed: Int) {
            isJoined = true
            LogUtil.e("Joined Channel $channel")
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            LogUtil.e("Remote user offline $uid $reason")
            activity?.runOnUiThread { remoteSurfaceView!!.visibility = GONE }
        }
    }

    private fun setupRemoteVideo(uid: Int) {
        remoteSurfaceView = SurfaceView(requireContext())
        remoteSurfaceView!!.setZOrderMediaOverlay(true)
        viewBinding.remoteVideoViewContainer.addView(remoteSurfaceView)
        agoraEngine!!.setupRemoteVideo(
            VideoCanvas(
                remoteSurfaceView,
                VideoCanvas.RENDER_MODE_FIT,
                uid
            )
        )
        // Display RemoteSurfaceView.
        viewBinding.remoteVideoViewContainer.visibility = View.VISIBLE
    }

    private fun setupLocalVideo() {
        localSurfaceView = SurfaceView(requireContext())
        viewBinding.localVideoViewContainer.addView(localSurfaceView)
        // Pass the SurfaceView object to Agora so that it renders the local video.
        agoraEngine!!.setupLocalVideo(
            VideoCanvas(
                localSurfaceView,
                VideoCanvas.RENDER_MODE_HIDDEN,
                0
            )
        )
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<UpdateViewModel> {
        return UpdateViewModel::class.java
    }

    override fun onDestroy() {
        super.onDestroy()
        agoraEngine!!.stopPreview()
        agoraEngine!!.leaveChannel()

        Thread {
            RtcEngine.destroy()
            agoraEngine = null
        }.start()
    }
}
