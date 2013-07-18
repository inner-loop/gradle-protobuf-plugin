package io.innerloop.gradle.plugin.protobuf

import org.gradle.api.tasks.Input

public class ProtobufExtension {

    public static final String NAME = 'protobuf'

    @Input String protocExecutable = 'protoc'

    @Input String generatedSrcDir = 'build/generated-sources'

    @Input String srcDir = 'src/main/proto'

    @Input String[] languages = 'java'
}
