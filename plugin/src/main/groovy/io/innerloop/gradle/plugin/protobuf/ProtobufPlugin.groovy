package io.innerloop.gradle.plugin.protobuf;

import org.gradle.api.*;

class ProtobufPlugin implements Plugin {
    def void apply(Project project) {
        project.task('protobuf') << {
            println 'Executing gradle protobuf plugin'
        }
    }
}
