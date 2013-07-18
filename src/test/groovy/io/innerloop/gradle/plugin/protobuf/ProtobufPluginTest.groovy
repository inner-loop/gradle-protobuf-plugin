package io.innerloop.gradle.plugin.protobuf

import io.innerloop.gradle.plugin.protobuf.tasks.ProtocCompileTask
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import static junit.framework.TestCase.assertNotNull
import static junit.framework.TestCase.assertTrue

class ProtobufPluginTest  {

    @Test
    public void testCompileTaskIsLoaded() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'protobuf'

        assertTrue(project.tasks.compileProto instanceof ProtocCompileTask)
    }

    @Test
    public void testProtobufExtensionIsAddedToProject() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'protobuf'

        assertNotNull(project.protobuf)
    }

    @Test
    public void testLanguageSupportIsAddedToExtension() {
        Project project = ProjectBuilder.builder().build()
        project.apply plugin: 'protobuf'

        project.protobuf.languages = ['java','cpp']

        assertTrue(project.protobuf.languages.contains('cpp'))
    }
}
