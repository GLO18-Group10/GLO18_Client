/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.Logic;

/**
 *
 * @author Nick
 */
public class Customer extends User {

    private String birthday;
    private String name;
    private String address;

    public Customer(String ID, LogicFacade logic) {
        super(ID, logic);
    }

    public String getBirthday() {
        //If birthday is null, get the birthday from the server
        if (birthday == null) {
            String data[] = logic.toProtocol01();
            setBirthday(data[1]);
        }
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        //If name is null, get the name from the server
        if (name == null) {
            String data[] = logic.toProtocol01();
            setName(data[0]);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        //If address is null, get the address from the server
        if (address == null) {
            String data[] = logic.toProtocol01();
            setAddress(data[3]);
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
