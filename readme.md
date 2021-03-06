# MITM Rest Debugging Proxy

Based on [LittleProxy](https://github.com/adamfisk/LittleProxy), [LittleProxy-mitm](https://github.com/ganskef/LittleProxy-mitm), and wrapped with [Spring](https://spring.io/) for configuration. (Replaces my old deprecated project: [mitm-project](https://github.com/paul-nelson-baker/mitm-project))

## What to do
You need to run the server to generate the certificates to debug HTTPS traffic. The simplest way is to run `start-server.sh`, or run `mvn spring-boot:run`. The default directory is created `~/.mitm-keystore/` and a `.p12` and a `.pem` file are created. The next thing you need to do is import the certificate into your keystore. You can import these into your browser or system depending on your application. I've found that the most difficult keystore to import into is java's because you need to piece together what/where the java keystore is, which is difficult if you are just looking into how to debug a rest call and don't know anything about mitm.

## Importing into Java Keystore
### OS X and Linux
The keystore tool should already be located in your path. From the terminal, all you need to do is use the `keytool` command. On OSX the default `cacerts` file can be found with this command `$(/usr/libexec/java_home)/jre/lib/security/cacerts` and on linux `$(readlink -f /usr/bin/java | sed "s:bin/java::")lib/security/cacerts`. You can run the shellscript `import-to-keystore.sh`. The commands to find the cacerts file were found [here](https://stackoverflow.com/a/11937940/1478636).
### Windows
The keystore tool should be in your path as well, however, locating your `cacerts` file might be a little more tricky. Microsoft has some documentation about how to find your cacerts file: [here](https://docs.microsoft.com/en-us/azure/java-add-certificate-ca-store).
### REGARDLESS OF OS
When you start the proxy, we'll try to locate your `cacerts` file. If we can find it, we'll print out the command you need to run to console.