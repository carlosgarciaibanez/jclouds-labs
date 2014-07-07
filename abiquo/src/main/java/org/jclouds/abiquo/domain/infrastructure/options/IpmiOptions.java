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
package org.jclouds.abiquo.domain.infrastructure.options;

import org.jclouds.http.options.BaseHttpRequestOptions;

/**
 * Available options to query ipmi config.
 */
public class IpmiOptions extends BaseHttpRequestOptions {
   public static Builder builder() {
      return new Builder();
   }

   @Override
   protected Object clone() throws CloneNotSupportedException {
      IpmiOptions options = new IpmiOptions();
      options.queryParameters.putAll(queryParameters);
      return options;
   }

   public static class Builder {
      private Integer port;

      /**
       * Set the optional hypervisor port.
       */
      public Builder port(final int port) {
         this.port = port;
         return this;
      }

      public IpmiOptions build() {
         IpmiOptions options = new IpmiOptions();
         if (port != null) {
            options.queryParameters.put("port", port.toString());
         }

         return options;
      }
   }
}
