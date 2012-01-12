/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sleuthkit.autopsy.datamodel;

import java.util.Map;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Sheet;

public class KeyValueNode extends AbstractNode {
    
    KeyValueThing thing;
    
    public KeyValueNode(KeyValueThing thing, Children children) {
        super(children);
        this.setName(thing.getName());
        this.thing = thing;
    }
    
    @Override
    protected Sheet createSheet() {
        Sheet s = super.createSheet();
        Sheet.Set ss = s.get(Sheet.PROPERTIES);
        if (ss == null) {
            ss = Sheet.createPropertiesSet();
            s.put(ss);
        }
        
        // table view drops first column of properties under assumption
        // that it contains the node's name
        ss.put(new NodeProperty("Name", "Name", "n/a", thing.getName()));
        
        for (Map.Entry<String, Object> entry : thing.getMap().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            ss.put(new NodeProperty(key, key, "n/a", value));
        }

        return s;
    }
}