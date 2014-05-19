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
package org.jclouds.dmtf;

/**
 * Constants used by DMTF.
 *
 * @author grkvlt@apache.org
 */
public final class DMTFConstants {

   public static final String OVF_NS = "http://schemas.dmtf.org/ovf/envelope/1";

   public static final String OVF_ENV_NS = "http://schemas.dmtf.org/ovf/environment/1";

   public static final String CIM_NS = "http://schemas.dmtf.org/wbem/wscim/1/common";
   
   public static final String CIM_VSSD_NS = "http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_VirtualSystemSettingData";
   
   public static final String CIM_RASD_NS = "http://schemas.dmtf.org/wbem/wscim/1/cim-schema/2/CIM_ResourceAllocationSettingData";

   private DMTFConstants() {
      throw new AssertionError("intentionally unimplemented");
   }
}
