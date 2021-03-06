/*
 * Copyright (C) 2014 The Dagger Authors.
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

package dagger.internal.codegen.componentgenerator;

import static com.google.common.base.Verify.verify;
import static dagger.internal.codegen.writing.ComponentNames.getRootComponentClassName;

import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.TypeSpec;
import dagger.Component;
import dagger.internal.codegen.base.SourceFileGenerator;
import dagger.internal.codegen.binding.BindingGraph;
import dagger.internal.codegen.langmodel.DaggerElements;
import dagger.internal.codegen.writing.ComponentImplementation;
import javax.annotation.processing.Filer;
import javax.inject.Inject;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;

/** Generates the implementation of the abstract types annotated with {@link Component}. */
final class ComponentGenerator extends SourceFileGenerator<BindingGraph> {
  private final ComponentImplementationFactory componentImplementationFactory;

  @Inject
  ComponentGenerator(
      Filer filer,
      DaggerElements elements,
      SourceVersion sourceVersion,
      ComponentImplementationFactory componentImplementationFactory) {
    super(filer, elements, sourceVersion);
    this.componentImplementationFactory = componentImplementationFactory;
  }

  @Override
  public Element originatingElement(BindingGraph input) {
    return input.componentTypeElement();
  }

  @Override
  public ImmutableList<TypeSpec.Builder> topLevelTypes(BindingGraph bindingGraph) {
    ComponentImplementation componentImplementation =
        componentImplementationFactory.createComponentImplementation(bindingGraph);
    verify(
        componentImplementation
            .name()
            .equals(getRootComponentClassName(bindingGraph.componentDescriptor())));
    return ImmutableList.of(componentImplementation.generate().toBuilder());
  }
}
