package user_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user_service.entity.UserEntity;

@Repository
public interface UserRespository extends JpaRepository<UserEntity,Long> {


}
