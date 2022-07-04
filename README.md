# Coffree
## Intormation
A java ejb mini web app


IDE: NetBean


Language: JAVA


Local Server: Payara Server 5.2022.2


Database: MySQL (in XAMPP)


## configuration of Database
username: root



password: 


```
CREATE TABLE `coffee` (
  `id` int(11) NOT NULL,
  `u_name` varchar(20) NOT NULL,
  `c_name` varchar(20) NOT NULL,
  `sugar` int(11) NOT NULL,
  `temperature` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```



## 2 reusable UI
Coffee List Page
![alt text](https://github.com/MaaZiJyun/Coffree/blob/main/images/1656562380757.png)


Coffee Form Page
![alt text](https://github.com/MaaZiJyun/Coffree/blob/main/images/1656562413847.png)

## 5 Functions
1. Insert Coffee
2. Delete Coffee
3. Update Coffee
4. Search Coffee
5. View Coffee
