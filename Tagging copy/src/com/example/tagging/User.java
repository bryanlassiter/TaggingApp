package com.example.tagging;

public class User {

        private long id;
        private String firstName;
        private String lastName;
        private String username;
        private String Password;
        
        public User(long id, String firstName, String lastName, String username,
                        String Password)
        {
                this.id = id;
                this.firstName = firstName;
                this.lastName = lastName;
                this.username = username;
                this.Password = Password;
        }
        public long getId() {
                return id;
        }
        public void setId(long id) {
                this.id = id;
        }
        public String getFirstName() {
                return firstName;
        }
        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }
        public String getLastName() {
                return lastName;
        }
        public void setLastName(String lastName) {
                this.lastName = lastName;
        }
        public String getUsername() {
                return username;
        }
        public void setUsername(String username) {
                this.username = username;
        }
        public String getPassword() {
                return Password;
        }
        public void setPassword(String Password) {
                this.Password = Password;
        }
              
}