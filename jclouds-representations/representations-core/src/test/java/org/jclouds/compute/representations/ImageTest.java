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
package org.jclouds.compute.representations;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


@Test
public class ImageTest {

   @Test
   void testToJson() {
      Image image = Image.builder().id("1").name("My image").version("1.0")
                                   .operatingSystem(OperatingSystem.builder()
                                           .family("UBUNTU")
                                           .is64Bit(true)
                                           .build())
                                   .build();

      Gson gson = new GsonBuilder().create();
      JsonElement json = gson.toJsonTree(image);
      assertNotNull(json);
      assertEquals("1", json.getAsJsonObject().get("id").getAsString());
      assertEquals("My image", json.getAsJsonObject().get("name").getAsString());
      assertEquals("UBUNTU", json.getAsJsonObject().getAsJsonObject("operatingSystem").get("family").getAsString());
   }

   @Test
   void testFromJson() throws IOException {
      Gson gson = new GsonBuilder().create();
      String json = Resources.toString(getResource("compute/Image-stub-1.json"), Charsets.UTF_8);
      Image image = gson.fromJson(json, Image.class);
      assertNotNull(image);
      assertEquals("1", image.getId());
   }
}
