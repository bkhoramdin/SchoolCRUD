/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.schools1;

/**
 *
 * @author Babak
 */
public class School {

    

        private String id;
        private String name;
        private String code;
        private String address;
        private String emailDomain;

        public String getName() {

            return name;
        }

        public void setName(String name) {

            this.name = name;
        }

        public String getCode() {

            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmailDomain() {
            return emailDomain;

        }

        public void setEmailDomain(String emailDomain) {
            this.emailDomain = emailDomain;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }
