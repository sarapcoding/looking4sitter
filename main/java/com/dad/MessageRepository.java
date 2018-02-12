package com.dad;
import org.springframework.data.repository.CrudRepository;
import com.dad.Message;
public interface MessageRepository extends CrudRepository<Message,Long>{

}
