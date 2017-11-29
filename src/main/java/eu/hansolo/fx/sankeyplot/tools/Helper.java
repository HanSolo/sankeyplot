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

package eu.hansolo.fx.sankeyplot.tools;

import javafx.scene.paint.Color;

import java.util.Locale;
import java.util.function.Predicate;


public class Helper {
    public  static final String[] ABBREVIATIONS = { "k", "M", "G", "T", "P", "E", "Z", "Y" };

    public static final int clamp(final int MIN, final int MAX, final int VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }
    public static final long clamp(final long MIN, final long MAX, final long VALUE) {
        if (VALUE < MIN) return MIN;
        if (VALUE > MAX) return MAX;
        return VALUE;
    }
    public static final double clamp(final double MIN, final double MAX, final double VALUE) {
        if (Double.compare(VALUE, MIN) < 0) return MIN;
        if (Double.compare(VALUE, MAX) > 0) return MAX;
        return VALUE;
    }

    public static final Color getColorWithOpacity(final Color COLOR, final double OPACITY) {
        double red     = COLOR.getRed();
        double green   = COLOR.getGreen();
        double blue    = COLOR.getBlue();
        double opacity = clamp(0, 1, OPACITY);
        return Color.color(red, green, blue, opacity);
    }

    public static final String format(final double NUMBER, final int DECIMALS) {
        return format(NUMBER, clamp(0, 12, DECIMALS), Locale.US);
    }
    public static final String format(final double NUMBER, final int DECIMALS, final Locale LOCALE) {
        String formatString = new StringBuilder("%.").append(clamp(0, 12, DECIMALS)).append("f").toString();
        double value;
        for(int i = ABBREVIATIONS.length - 1 ; i >= 0; i--) {
            value = Math.pow(1000, i+1);
            if (Double.compare(NUMBER, -value) <= 0 || Double.compare(NUMBER, value) >= 0) {
                return String.format(LOCALE, formatString, (NUMBER / value)) + ABBREVIATIONS[i];
            }
        }
        return String.format(LOCALE, formatString, NUMBER);
    }

    public static final double[] calcAutoScale(final double MIN_VALUE, final double MAX_VALUE) {
        double maxNoOfMajorTicks = 10;
        double maxNoOfMinorTicks = 10;
        double minorTickSpace    = 1;
        double majorTickSpace    = 10;
        double niceRange         = (Helper.calcNiceNumber((MAX_VALUE - MIN_VALUE), false));
        majorTickSpace           = Helper.calcNiceNumber(niceRange / (maxNoOfMajorTicks - 1), true);
        minorTickSpace           = Helper.calcNiceNumber(majorTickSpace / (maxNoOfMinorTicks - 1), true);
        double niceMinValue      = (Math.floor(MIN_VALUE / majorTickSpace) * majorTickSpace);
        double niceMaxValue      = (Math.ceil(MAX_VALUE / majorTickSpace) * majorTickSpace);

        return new double[] { minorTickSpace, majorTickSpace, niceMinValue, niceMaxValue };
    }

    public static final double calcNiceNumber(final double RANGE, final boolean ROUND) {
        double niceFraction;
        double exponent = Math.floor(Math.log10(RANGE));   // exponent of range
        double fraction = RANGE / Math.pow(10, exponent);  // fractional part of range

        if (ROUND) {
            if (Double.compare(fraction, 1.5) < 0) {
                niceFraction = 1;
            } else if (Double.compare(fraction, 3)  < 0) {
                niceFraction = 2;
            } else if (Double.compare(fraction, 7) < 0) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        } else {
            if (Double.compare(fraction, 1) <= 0) {
                niceFraction = 1;
            } else if (Double.compare(fraction, 2) <= 0) {
                niceFraction = 2;
            } else if (Double.compare(fraction, 5) <= 0) {
                niceFraction = 5;
            } else {
                niceFraction = 10;
            }
        }
        return niceFraction * Math.pow(10, exponent);
    }

    public static <T> Predicate<T> not(Predicate<T> predicate) { return predicate.negate(); }
}
