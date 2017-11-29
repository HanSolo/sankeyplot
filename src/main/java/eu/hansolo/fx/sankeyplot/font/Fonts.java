/*
 * Copyright (c) 2017 by Gerrit Grunwald
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

package eu.hansolo.fx.sankeyplot.font;

import javafx.scene.text.Font;


public class Fonts {
    private static final String LATO_LIGHT_NAME;
    private static final String LATO_REGULAR_NAME;
    private static final String LATO_BOLD_NAME;

    private static String latoLightName;
    private static String latoRegularName;
    private static String latoBoldName;


    static {
        try {
            latoLightName              = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/sankeychart/font/Lato-Lig.otf"), 10).getName();
            latoRegularName            = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/sankeychart/font/Lato-Reg.otf"), 10).getName();
            latoBoldName               = Font.loadFont(Fonts.class.getResourceAsStream("/eu/hansolo/fx/sankeychart/font/Lato-Bol.otf"), 10).getName();
        } catch (Exception exception) { }
        LATO_LIGHT_NAME               = latoLightName;
        LATO_REGULAR_NAME             = latoRegularName;
        LATO_BOLD_NAME                = latoBoldName;
    }


    // ******************** Methods *******************************************
    public static Font latoLight(final double SIZE) { return new Font(LATO_LIGHT_NAME, SIZE); }
    public static Font latoRegular(final double SIZE) { return new Font(LATO_REGULAR_NAME, SIZE); }
    public static Font latoBold(final double SIZE) { return new Font(LATO_BOLD_NAME, SIZE); }
}
