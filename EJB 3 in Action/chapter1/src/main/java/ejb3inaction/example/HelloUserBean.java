/**
 *  EJB 3 in Action
 *  Book: http://manning.com/panda2/
 *  Code: http://code.google.com/p/action-bazaar/
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package ejb3inaction.example;

import javax.ejb.Stateless;

/**
 * HelloUserBean
 * @author rcuprak
 */
@Stateless
public class HelloUserBean implements HelloUser {

    /**
     * Returns a hello message to the caller
     * @param name - name
     * @return String
     */
    public String sayHello(String name) {
        return "Hello " + name + " welcome to EJB 3.1!";
    }

}

