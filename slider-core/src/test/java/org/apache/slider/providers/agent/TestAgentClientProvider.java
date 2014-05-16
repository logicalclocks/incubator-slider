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
package org.apache.slider.providers.agent;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.slider.common.tools.SliderFileSystem;
import org.apache.slider.common.tools.SliderUtils;
import org.junit.Test;

import java.io.InputStream;
import java.util.Set;

/**
 *
 */
public class TestAgentClientProvider {
  @Test
  public void testGetApplicationTags () throws Exception {
    Configuration configuration = new Configuration();
    FileSystem fs = FileSystem.getLocal(configuration);
    SliderFileSystem sliderFileSystem = new SliderFileSystem(fs, configuration);

    AgentClientProvider provider = new AgentClientProvider(null);
    Set<String> tags = provider.getApplicationTags(sliderFileSystem,
      "target/test-classes/org/apache/slider/common/tools/test.zip");
    assert tags != null;
    assert !tags.isEmpty();
    assert tags.contains("Name: STORM");
    assert tags.contains("Description: Apache Hadoop Stream processing framework");
    assert tags.contains("Version: 0.9.1.2.1");

  }
}
