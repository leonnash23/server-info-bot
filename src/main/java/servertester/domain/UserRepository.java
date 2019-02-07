package servertester.domain;

public interface UserRepository {

    User getUser(long id);

    @SuppressWarnings("UnusedReturnValue")
    User save(User user);

}
