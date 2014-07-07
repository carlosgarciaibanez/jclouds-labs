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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.abiquo.domain.infrastructure;

import org.jclouds.abiquo.AbiquoApi;
import org.jclouds.abiquo.domain.DomainWrapper;
import org.jclouds.rest.ApiContext;

import com.abiquo.server.core.infrastructure.storage.StorageDeviceMetadataDto;

/**
 * metadata describing a Storage Device.
 */
public class StorageDeviceMetadata extends DomainWrapper<StorageDeviceMetadataDto> {
   /**
    * Constructor to be used only by the builder.
    */
   protected StorageDeviceMetadata(final ApiContext<AbiquoApi> context, final StorageDeviceMetadataDto target) {
      super(context, target);
   }

   // Delegate methods

   public String getType() {
      return target.getType();
   }

   public int getDefaultManagementPort() {
      return target.getDefaultManagementPort();
   }

   public int getDefaultIscsiPort() {
      return target.getDefaultServicePort();
   }

   public boolean requiresAuthentication() {
      return target.isRequiresAuthentication();
   }

}
