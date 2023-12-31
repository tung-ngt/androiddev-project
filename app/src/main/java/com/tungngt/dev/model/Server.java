package com.tungngt.dev.model;

import java.io.Serializable;
import java.util.Objects;

public class Server implements Comparable<Server>, Serializable {
        public String name;
        public String image_irl;
        public String server_url;
        public String server_port;
        public Integer color;
        public Server(String name, String image_irl, String server_url, String server_port, Integer color){
            this.name = name;
            this.image_irl = image_irl;
            this.server_url = server_url;
            this.server_port = server_port;
            this.color = color;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Server)) return false;
            Server server = (Server) o;
            return Objects.equals(name, server.name) &&
                    Objects.equals(image_irl, server.image_irl);
        }
        @Override
        public int compareTo(Server o) {
            if (this.name.compareTo(o.name) == 0) {
                return this.image_irl.compareTo(o.image_irl);
            }

            return this.name.compareTo(o.name);
        }
}

