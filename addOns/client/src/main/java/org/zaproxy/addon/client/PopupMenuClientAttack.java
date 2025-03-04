/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2023 The ZAP Development Team
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
package org.zaproxy.addon.client;

import java.awt.Component;
import java.awt.event.ActionEvent;
import org.parosproxy.paros.Constant;

public class PopupMenuClientAttack extends PopupMenuItemClient {

    private static final long serialVersionUID = 1L;

    public PopupMenuClientAttack(ClientMapPanel clientMapPanel) {
        super(Constant.messages.getString("client.tree.popup.attack"), clientMapPanel);
    }

    @Override
    public boolean isEnableForComponent(Component invoker) {
        boolean enabled = super.isEnableForComponent(invoker);
        if (enabled) {
            // For now its a placeholder / tease ;)
            this.setEnabled(false);
        }
        return enabled;
    }

    @Override
    public void performAction(ActionEvent e) {
        // Do nothing for now
    }
}
