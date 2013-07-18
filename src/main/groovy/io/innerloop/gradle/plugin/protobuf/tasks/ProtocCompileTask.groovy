package io.innerloop.gradle.plugin.protobuf.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction


class ProtocCompileTask extends DefaultTask {

    @TaskAction
    def compileProto() {

        def src = new File(project.protobuf.srcDir)

        if (!src.exists()) {
            throw new GradleException("Directory '$project.protobuf.srcDir' not found.")
        }

        def List<String> protoFiles = []

        src.eachFileMatch(~/.*\.proto/) {
           file -> protoFiles.add(project.projectDir.toURI().relativize(file.toURI()).getPath())
        }

        project.protobuf.languages.each {
            language -> invokeProtoc(project.protobuf.protocExecutable, project.protobuf.srcDir, language, project.protobuf.generatedSrcDir, protoFiles)
        }
    }

    def invokeProtoc(String protocExecutable, srcDir, language, generatedSourceDir, List<String> protoFiles) {
        def protoFilesAsString = protoFiles.join(' ')
        def genSrc = new File(generatedSourceDir + '/' + language)

        if (!genSrc.exists()) {
            genSrc.mkdirs()
        }
        def command = "$protocExecutable --proto_path=$srcDir  --${language}_out=$generatedSourceDir/$language $protoFilesAsString"

        logger.info(command)

        def process = command.execute(null, project.projectDir)

        process.waitFor()

        if (process.exitValue() != 0) {
            throw new GradleException(process.err.text)
        }
    }
}