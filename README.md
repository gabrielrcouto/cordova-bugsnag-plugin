
## Cordova Plugin that wraps Bugsnag SDK for Android and iOS

#### Install

```
cordova plugin add https://github.com/###
```

#### Usage

**window.cordovaBugsnag:**

- startBugsnagWithApiKey(apiKey)

#### iOS

-   Under "Build Settings" add "-ObjC" to "Other Linker Flags" (search for "ldflags")

-   Under "General" add "libc++" and "SystemConfiguration.framework" to "Linked Frameworks and Libraries"

-   Add a build phase to upload the symbolication information to Bugsnag

    From the same "Build Phases" screen, click the plus in the bottom right of the screen labelled "Add Build Phase", then select "Add Run Script". Then expand the newly added "Run Script" section, and set the shell to `/usr/bin/ruby` and copy the following script into the text box,

    ```ruby
    fork do
      Process.setsid
      STDIN.reopen("/dev/null")
      STDOUT.reopen("/dev/null", "a")
      STDERR.reopen("/dev/null", "a")

      require 'shellwords'

      Dir["#{ENV["DWARF_DSYM_FOLDER_PATH"]}/*/Contents/Resources/DWARF/*"].each do |dsym|
        system("curl -F dsym=@#{Shellwords.escape(dsym)} -F projectRoot=#{Shellwords.escape(ENV["PROJECT_DIR"])} https://upload.bugsnag.com/")
      end
    end
    ```

#### Android

- Paste the code below into Cordova Project config.xml

```xml
<gap:config-file platform="android" parent="application" mode="merge">
      <meta-data
        android:name="com.bugsnag.android.API_KEY"
        android:value="PASTE_YOUR_API_KEY_HERE"/>
</gap:config-file
```

##### Keywords
bugsnag, plugin cordova, phonegap, android, ios