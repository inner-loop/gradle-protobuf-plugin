package io.innerloop.gradle.plugin.protobuf

import io.innerloop.gradle.plugin.protobuf.tasks.ProtocCompileTask;
import org.gradle.api.*
import org.gradle.api.plugins.JavaPlugin
import org.gradle.plugins.cpp.CppPlugin;

class ProtobufPlugin implements Plugin<Project> {

    public static final String COMPILE_PROTO_TASK_NAME = 'compileProto'

    @Override
    def void apply(Project project) {

        project.extensions.create(ProtobufExtension.NAME, ProtobufExtension)

        applyTasks(project)
    }

    void applyTasks(final Project project) {
        project.task(COMPILE_PROTO_TASK_NAME, type: ProtocCompileTask) {
            group = 'protobuf'
            description = 'Compile .proto files into the language(s) configured.'
        }

        if (project.protobuf.languages.contains('java')) {
            project.plugins.apply(JavaPlugin)
            project.tasks.findByName(JavaPlugin.COMPILE_JAVA_TASK_NAME).dependsOn(COMPILE_PROTO_TASK_NAME)
        }

        if (project.protobuf.languages.contains('cpp')) {
            project.plugins.apply(CppPlugin)
            project.tasks.findByName('compileMain').dependsOn(COMPILE_PROTO_TASK_NAME)
        }

        if (project.protobuf.languages.contains('python')) {
            println 'innerloop is currently developing a python plugin. For now there is no support.'
//            project.plugins.apply(PythonPlugin)
//            project.tasks.findByName(PythonPlugin.GENERATE_SOURCES_TASK_NAME).dependsOn(COMPILE_PROTO_TASK_NAME)
        }
    }
}
