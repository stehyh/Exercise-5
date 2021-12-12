package com.mygame;

public class PortAudio
{
    public final static int FLAG_CLIP_OFF = (1 << 0);
    public final static int FLAG_DITHER_OFF = (1 << 1);

    /** Sample Formats */
    public final static int FORMAT_FLOAT_32 = (1 << 0);
    public final static int FORMAT_INT_32 = (1 << 1); // not supported
    public final static int FORMAT_INT_24 = (1 << 2); // not supported
    public final static int FORMAT_INT_16 = (1 << 3);
    public final static int FORMAT_INT_8 = (1 << 4); // not supported
    public final static int FORMAT_UINT_8 = (1 << 5); // not supported

    /** These HOST_API_TYPES will not change in the future. */
    public final static int HOST_API_TYPE_DEV = 0;
    public final static int HOST_API_TYPE_DIRECTSOUND = 1;
    public final static int HOST_API_TYPE_MME = 2;
    public final static int HOST_API_TYPE_ASIO = 3;
    /** Apple Sound Manager. Obsolete. */
    public final static int HOST_API_TYPE_SOUNDMANAGER = 4;
    public final static int HOST_API_TYPE_COREAUDIO = 5;
    public final static int HOST_API_TYPE_OSS = 7;
    public final static int HOST_API_TYPE_ALSA = 8;
    public final static int HOST_API_TYPE_AL = 9;
    public final static int HOST_API_TYPE_BEOS = 10;
    public final static int HOST_API_TYPE_WDMKS = 11;
    public final static int HOST_API_TYPE_JACK = 12;
    public final static int HOST_API_TYPE_WASAPI = 13;
    public final static int HOST_API_TYPE_AUDIOSCIENCE = 14;
    public final static int HOST_API_TYPE_COUNT = 15;

    static
    {
        System.out.println(System.getProperty("java.library.path"));

        String os = System.getProperty( "os.name" ).toLowerCase();
        // On Windows we have separate libraries for 32 and 64-bit JVMs.
        if( os.indexOf( "win" ) >= 0 )
        {
            if( System.getProperty( "os.arch" ).contains( "64" ) )
            {
                System.loadLibrary( "jportaudio_x64" );
            }
            else
            {
                System.loadLibrary( "jportaudio_x86" );
            }
        }
        else
        {
            //System.load("/usr/local/Cellar/portaudio/19.6.0/lib/libportaudio.2.dylib");
            System.loadLibrary( "portaudio" );
        }
        System.out.println( "---- JPortAudio version " + getVersion() + ", "
            + getVersionText() );
    }

    /**
     * @return the release number of the currently running com.mygame.PortAudio build, eg
     *         1900.
     */
    public native static int getVersion();

    /**
     * @return a textual description of the current com.mygame.PortAudio build, eg
     *         "com.mygame.PortAudio V19-devel 13 October 2002".
     */
    public native static String getVersionText();

    /**
     * Library initialization function - call this before using com.mygame.PortAudio. This
     * function initializes internal data structures and prepares underlying
     * host APIs for use. With the exception of getVersion(), getVersionText(),
     * and getErrorText(), this function MUST be called before using any other
     * com.mygame.PortAudio API functions.
     */
    public native static void initialize();

    /**
     * Library termination function - call this when finished using com.mygame.PortAudio.
     * This function deallocates all resources allocated by com.mygame.PortAudio since it
     * was initialized by a call to initialize(). In cases where Pa_Initialise()
     * has been called multiple times, each call must be matched with a
     * corresponding call to terminate(). The final matching call to terminate()
     * will automatically close any com.mygame.PortAudio streams that are still open.
     */
    public native static void terminate();

    /**
     * @return the number of available devices. The number of available devices
     *         may be zero.
     */
    public native static int getDeviceCount();


    /**
     * @param hostApiType
     *            A unique host API identifier, for example
     *            HOST_API_TYPE_COREAUDIO.
     * @return a runtime host API index
     */
    public native static int hostApiTypeIdToHostApiIndex( int hostApiType );

    /**
     * @param hostApiIndex
     *            A valid host API index ranging from 0 to (getHostApiCount()-1)
     * @param apiDeviceIndex
     *            A valid per-host device index in the range 0 to
     *            (getHostApiInfo(hostApi).deviceCount-1)
     * @return standard com.mygame.PortAudio device index
     */
    public native static int hostApiDeviceIndexToDeviceIndex( int hostApiIndex,
        int apiDeviceIndex );

    public native static int getDefaultInputDevice();

    public native static int getDefaultOutputDevice();

    public native static int getDefaultHostApi();

    /**
     * @param inputStreamParameters
     *            input description, may be null
     * @param outputStreamParameters
     *            output description, may be null
     * @param sampleRate
     *            typically 44100 or 48000, or maybe 22050, 16000, 8000, 96000
     * @return 0 if supported or a negative error
     */
    //public native static int isFormatSupported(
    //    StreamParameters inputStreamParameters,
    //    StreamParameters outputStreamParameters, int sampleRate );
    //
    //private native static void openStream( BlockingStream blockingStream,
    //    StreamParameters inputStreamParameters,
    //    StreamParameters outputStreamParameters, int sampleRate,
    //    int framesPerBuffer, int flags );
    //
    ///**
    // *
    // * @param inputStreamParameters
    // *            input description, may be null
    // * @param outputStreamParameters
    // *            output description, may be null
    // * @param sampleRate
    // *            typically 44100 or 48000, or maybe 22050, 16000, 8000, 96000
    // * @param framesPerBuffer
    // * @param flags
    // * @return
    // */
    //public static BlockingStream openStream(
    //    StreamParameters inputStreamParameters,
    //    StreamParameters outputStreamParameters, int sampleRate,
    //    int framesPerBuffer, int flags )
    //{
    //    BlockingStream blockingStream = new BlockingStream();
    //    openStream( blockingStream, inputStreamParameters,
    //        outputStreamParameters, sampleRate, framesPerBuffer, flags );
    //    return blockingStream;
    //}

}