## Spring事务传播类型：Requires_NEW
1. 无论父方法是否存在事务，子方法都新建一个事务。
2. 此时父子方法事务是独立的，它们都不会互相影响。
3. 注意：子方法抛出异常的情况下，父方法需要进行捕获，否则父方法也会抛错回滚。