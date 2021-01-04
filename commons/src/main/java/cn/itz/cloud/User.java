package cn.itz.cloud;

/**
 * @PackageName: cn.itz.cloud
 * @ClassName: User
 * @Description: TODO
 * @Author: codeZhang
 * @DateTime: 2021/1/4 10:38
 * @Version 1.0
 */
public class User {
  Integer id;
  String name;
  String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User() {
  }

  public User(Integer id, String name, String password) {
    this.id = id;
    this.name = name;
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
