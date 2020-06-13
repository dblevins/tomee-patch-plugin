/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.tomee.patch.core;

import org.objectweb.asm.ModuleVisitor;

public class ModuleTransformer extends ModuleVisitor {

    public ModuleTransformer(final int api, final ModuleVisitor moduleVisitor) {
        super(api, moduleVisitor);
    }

    @Override
    public void visitExport(String packaze, final int access, final String... modules) {
        packaze = new Replace(packaze)
                .replace("javax/xml/bind", "jakarta/xml/bind")
                .get();
        super.visitExport(packaze, access, modules);
    }

    @Override
    public void visitProvide(String service, final String... providers) {
        service = new Replace(service)
                .replace("javax/xml/bind", "jakarta/xml/bind")
                .replace("javax/xml/soap", "jakarta/xml/soap")
                .get();
        super.visitProvide(service, providers);
    }

    @Override
    public void visitUse(String service) {
        service = new Replace(service)
                .replace("javax/xml/bind", "jakarta/xml/bind")
                .replace("javax/xml/soap", "jakarta/xml/soap")
                .get();
        super.visitUse(service);
    }
}