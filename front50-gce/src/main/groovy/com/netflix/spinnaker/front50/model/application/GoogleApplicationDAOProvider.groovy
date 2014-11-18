/*
 * Copyright 2014 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.front50.model.application

import com.google.api.services.datastore.client.DatastoreFactory
import com.google.api.services.datastore.client.DatastoreOptions
import com.netflix.spinnaker.front50.security.gce.GoogleNamedAccountCredentials
import org.springframework.stereotype.Component

@Component
class GoogleApplicationDAOProvider implements ApplicationDAOProvider<GoogleNamedAccountCredentials> {
  @Override
  boolean supports(Class<?> credentialsClass) {
    GoogleNamedAccountCredentials.isAssignableFrom(credentialsClass)
  }

  @Override
  ApplicationDAO getForAccount(GoogleNamedAccountCredentials credentials) {
    new GoogleApplicationDAO(datastoreFactory: DatastoreFactory.get(),
                             datastoreOptionsBuilder: new DatastoreOptions.Builder(),
                             credentials: credentials)
  }
}