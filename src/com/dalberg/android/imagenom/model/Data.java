/*
 * Copyright (C) 2014 Pat Dalberg 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dalberg.android.imagenom.model;

import java.util.ArrayList;

public class Data {
	public String id;
	public String title;
	public String description;
    public int datetime;
    public String type;
    public boolean animated;
    public int width;
    public int height;
    public int size;
    public int views;
    public int bandwidth;
    public String vote;
    public String section;
    public String account_url;
    public int ups;
    public int downs;
    public int score;
    public boolean is_album;
    public String cover;
    public String privacy;
    public String layout;
    public int images_count;
    public ArrayList<Image> images;
}
