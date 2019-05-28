package com.home.phoneotp;

import android.location.Location;
import android.provider.Contacts;

/**
 * Created by owner on 3/31/2018.
 */

    public class Restaurants {
        String name;
        String imageUrl;
        String catagory;
        Contacts contacts = new Contacts();
        Location location = new Location();
        Restaurants(){}
        public Restaurants(String name, String imageUrl, String catagory, Contacts contacts, Location location) {
            this.name = name;
            this.imageUrl = imageUrl;
            this.catagory = catagory;
            this.contacts = contacts;
            this.location = location;
        }
        public String getCatagory() {
            return catagory;
        }
        public void setCatagory(String catagory) {
            this.catagory = catagory;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getImageUrl() {
            return imageUrl;
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
        public Location getLocation() {
            return location;
        }
        public void setLocation(Location location) {
            this.location = location;
        }
        public Contacts getContacts() {
            return contacts;
        }
        public void setContacts(Contacts contacts) {
            this.contacts = contacts;
        }
    }

