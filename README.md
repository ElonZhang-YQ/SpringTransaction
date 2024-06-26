## Spring事务机制

### Spring事务传播机制
- [Required](https://github.com/ElonZhang-YQ/SpringTransaction/tree/main/RequiredTransactional)
- [RequiresNew](https://github.com/ElonZhang-YQ/SpringTransaction/tree/main/RequiresNewTransactional)
- [Nested](https://github.com/ElonZhang-YQ/SpringTransaction/tree/main/NestedTransactional)
- [Mandatory](https://github.com/ElonZhang-YQ/SpringTransaction/tree/main/MandatoryTransactional)
- [Supports](https://github.com/ElonZhang-YQ/SpringTransaction/tree/main/SupportsTransactional)
- [NotSupported](https://github.com/ElonZhang-YQ/SpringTransaction/tree/main/NotSupportedTransactional)
- [Never](https://github.com/ElonZhang-YQ/SpringTransaction/tree/main/NeverTransactional)

### Spring事务失效场景
- 抛出检查异常
  - 上面所有的测试用例中，抛出的异常都是RuntimeException，所以能够正常触发事务。
  - 如果`@Transactional`没有特殊指定的情况下，Spring只会在遇到运行时异常RuntimeException或者Error时进行回滚。
  - 如果想要支持其他异常回滚的情况下，注解配置rollbackFor即可
    ~~~java
    @Transactional(rollbackFor = Exception.class) 
    public void xxxxxMethod() {
    
    }
    ~~~
- 业务方法本身捕获了异常
  - 这种情况下出现会比较难以定位问题。
  - 比如：在Nested用例中，子事务抛出了一个RuntimeException，然后父方法进行了捕获处理。
  - 所以是父方法正常写入，子方法进行回滚。如果希望父方法事务也进行回滚，就不去进行异常捕获即可。
- 同一类中的方法调用
  ~~~java
  @Service
  public class DefaultTransactionService implements Service {
  
    public void saveUser() throws Exception {
        // do something
        doInsert();
    }
  
    public void doInsert() throws IOException {
        User user = new User();
        userService.insert(user);
        throw new IOException();
    }
  }
  ~~~
  - 在上述代码的情况下，也会导致事务失效
  - 因为Spring的事务管理功能是通过动态代理实现的，而Spring默认使用JDK动态代理，而JDK动态代理采用接口实现的方式，通过反射调用目标类。
  - 此时`saveUser()`方法实际调用的是`this.doInsert()`，直接走的是doInsert的逻辑，而不是走的切面逻辑。
  - 解决方法：在启动类中添加`@EnableAspectJAutoProxy(exposeProxy = true)`，会由Cglib代理实现。
- 方法使用final或者static关键字
  - 如果Spring使用了Cglib代理实现，然后业务方法中使用了final或者static，就会导致事务失效。
  - 因为Cglib生成的代理类无法重写final或者static定义的方法。
- 方法不是public
  - 如果方法不是Public的情况下，Spring事务也会失效。
  - Spring的事务管理源码`AbstractFallbackTransactionAttributeSource`中有判断`computeTransactionAttribute()`。
  - 如果目标方法不是公共的，则TransactionAttribute返回null。
- 错误使用事务传播机制
  - 就像上面捕获异常的那个问题，也是错误使用事务传播的机制。需要根据不同情况，选择不同的事务传播机制。
- 没有被Spring管理
  - 如果方法没有被Spring注解修饰，就不会被Spring加载成为Bean，这个类就不会被Spring管理了，事务自然就失效了。