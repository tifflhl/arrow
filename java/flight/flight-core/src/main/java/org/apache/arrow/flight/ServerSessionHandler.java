/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.arrow.flight;

/**
 * ServerSessionHandler interface to retrieve the current session ID.
 */
public interface ServerSessionHandler {
  /**
   * A session handler that does not support sessions.
   * It is used as the default handler for a Flight server with no session capabilities.
   */
  ServerSessionHandler NO_OP = new ServerSessionHandler() {
    @Override
    public String getSessionID() {
          return null;
      }

    @Override
    public boolean isValid(String sessionId) {
          return false;
      }
  };

  /**
   * Retrieves the current session ID. Generates a new session ID if there is no pre-existing
   * session ID.
   *
   * @return the current session ID.
   * @throws FlightRuntimeException with CallStatus {@code UNAUTHENTICATED} if session ID has expired.
   */
  String getSessionID();

  /**
   * Validates an incoming session ID. Returns true if sessionId is valid; returns false otherwise.
   *
   * @param sessionId the incoming session ID to validate.
   * @return true if incoming session ID is valid; false otherwise.
   */
  boolean isValid(String sessionId);
}
