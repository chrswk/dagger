/*
 * Copyright (C) 2020 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dagger.hilt.android.plugin

/**
 * Configuration options for the Hilt Gradle Plugin
 */
interface HiltExtension {

  /**
   * If set to `true`, Hilt will adjust the compile classpath such that it includes transitive
   * dependencies, ignoring `api` or `implementation` boundaries during compilation. You should
   * enable this option if your project has multiple level of transitive dependencies that contain
   * injected classes or entry points. See https://github.com/google/dagger/issues/1991 for more
   * context.
   */
  var enableExperimentalClasspathAggregation: Boolean

  /**
   * If set to `true`, Hilt will register a transform task that will rewrite `@AndroidEntryPoint`
   * annotated classes before the host-side JVM tests run. You should enable this option if you are
   * running Robolectric UI tests as part of your JUnit tests.
   */
  var enableTransformForLocalTests: Boolean
}

internal open class HiltExtensionImpl : HiltExtension {
  override var enableExperimentalClasspathAggregation: Boolean = false
  override var enableTransformForLocalTests: Boolean = false
}
