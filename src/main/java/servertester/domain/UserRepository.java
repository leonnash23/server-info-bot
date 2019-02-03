package servertester.domain;

public interface UserRepository {

    User getUser(long id);

    User save(User user);

}
