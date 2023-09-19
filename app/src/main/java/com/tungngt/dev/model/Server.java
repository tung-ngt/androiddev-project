package com.tungngt.dev.model;

import java.util.Objects;

public class Server implements Comparable<Server> {
        public String name;
        public String image_irl;
        public Server(String name, String image_irl){
            this.name = name;
            this.image_irl = image_irl;
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

