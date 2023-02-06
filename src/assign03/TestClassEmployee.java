package assign03;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Random;

/**
 * remade version of UOfUStudent class from assignment 02 used for testing assignment 3 priorityQueue
 */
public class TestClassEmployee {

        private String firstName;

        private String lastName;

        private int badgeID;

        /**
         * Creates an employee from the given first name, last name, and ID.
         *
         * @param firstName
         * @param lastName
         * @param id
         */
        public TestClassEmployee(String firstName, String lastName, int id) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.badgeID = id;
        }

        /**
         * Getter method for the first name field of this employee object.
         *
         * @return this employee's first name
         */
        public String getFirstName() {
            return this.firstName;
        }


        /**
         * Getter method for the last name field of this employee object.
         *
         * @return this employee's last name
         */
        public String getLastName() {
            return this.lastName;
        }


        /**
         * Getter method for the badge number field of this employee object.
         *
         * @return this employee's badge number
         */
        public int getBadgeID() {
            return this.badgeID;
        }

        /**
         * badge id cannot change
         *
         * @param firstName - updated first name
         * @param lastName - updated last name
         */
        public void updateName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        /**
         *
         * @param firstName - updated first name
         * @param lastName - updated last name
        */
        public String toString() {
            return this.firstName + " " + this.lastName  + String.format("%07d", this.badgeID) + ")";
        }

        /**
         * a class that implements the comparator interface
         * to compare two employee objects
         */
        static class sortById implements Comparator<TestClassEmployee> {

            /**
             * compares two employee objects from the o1 to o2 and returns a positive number
             * if o1 is bigger than o2. returns a negative number if o1 is smaller than o2. returns
             * 0 if o1 is equal to o2.
             *
             * @param o1 the first object to be compared.
             * @param o2 the second object to be compared.
             * @return an integer that reflects bigger, smaller, or equal
             */
            @Override
            public int compare(TestClassEmployee o1, TestClassEmployee o2) {
                return Integer.compare(o1.badgeID, o2.badgeID);
            }
        }
}

