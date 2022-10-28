# set-surface
Demonstrates error in setSurface when using ExoPlayer on Android OS 12

When running this app on Moto G60 with Android 12, ExoPlayer eventually throws an exception due to an error in MediaCodec.setSurface.

Surprisingly, setting forceDisableMediaCodecAsynchronousQueueing doesn't prevent this error.
