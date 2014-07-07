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
package org.jclouds.abiquo.binders.infrastructure;

import static org.jclouds.reflect.Reflection2.method;
import static org.testng.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.HttpMethod;

import org.jclouds.abiquo.features.InfrastructureApi;
import org.jclouds.reflect.Invocation;
import org.jclouds.rest.internal.GeneratedHttpRequest;
import org.testng.annotations.Test;

import com.abiquo.model.rest.RESTLink;
import com.abiquo.server.core.infrastructure.DatacenterDto;
import com.google.common.collect.ImmutableList;
import com.google.common.reflect.Invokable;

/**
 * Unit tests for the {@link BindSupportedDevicesLinkToPath} class.
 */
@Test(groups = "unit", testName = "BindSupportedDevicesLinkToPathTest")
public class BindSupportedDevicesLinkToPathTest {
   @Test(expectedExceptions = NullPointerException.class)
   public void testGetNewEnpointNullInput() {
      BindSupportedDevicesLinkToPath binder = new BindSupportedDevicesLinkToPath();
      binder.getNewEndpoint(null, null);
   }

   @Test(expectedExceptions = IllegalArgumentException.class)
   public void testGetNewEnpointInvalidInput() {
      BindSupportedDevicesLinkToPath binder = new BindSupportedDevicesLinkToPath();
      binder.getNewEndpoint(null, new Object());
   }

   public void testGetNewEnpoint() throws Exception {
      DatacenterDto datacenter = new DatacenterDto();
      datacenter.addLink(new RESTLink("devices", "http://foo/bar"));

      BindSupportedDevicesLinkToPath binder = new BindSupportedDevicesLinkToPath();

      Invokable<?, ?> withEndpointLink = method(InfrastructureApi.class, "listSupportedStorageDevices",
            DatacenterDto.class);

      GeneratedHttpRequest request = GeneratedHttpRequest.builder()
            .invocation(Invocation.create(withEndpointLink, ImmutableList.<Object> of(datacenter)))
            .method(HttpMethod.GET).endpoint(URI.create("http://foo/bar")).build();

      assertEquals(binder.getNewEndpoint(request, datacenter), "http://foo/bar/action/supported");
   }

   @Test(expectedExceptions = NullPointerException.class)
   public void testGetNewEnpointWithoutLink() throws Exception {
      DatacenterDto datacenter = new DatacenterDto();

      BindSupportedDevicesLinkToPath binder = new BindSupportedDevicesLinkToPath();

      Invokable<?, ?> withEndpointLink = method(InfrastructureApi.class, "listSupportedStorageDevices",
            DatacenterDto.class);

      GeneratedHttpRequest request = GeneratedHttpRequest.builder()
            .invocation(Invocation.create(withEndpointLink, ImmutableList.<Object> of(datacenter)))
            .method(HttpMethod.GET).endpoint(URI.create("http://foo/bar")).build();

      assertEquals(binder.getNewEndpoint(request, datacenter), "http://foo/bar/action/supported");
   }
}
