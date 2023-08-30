package com.stc.demo.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stc.demo.model.domain.DocumentType;
import com.stc.demo.model.domain.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
  	Long countByDocumentType(DocumentType documentType);
  	Optional<Item> findByItemId(int itemId);
  	
  	@Query(value="  select *  from item where parent_id=:id  ",nativeQuery = true)
  	List<Item> items(@Param("id") Integer id);
  	
  	@Query(value="  select i.item_id,i.item_name,d.document_type_name,group_concat(g.group_name) as `groups`,i.creation_date,f.files_binary,f.extenstion,f.size\r\n"
  			+ "from item i \r\n"
  			+ "join document_type d on d.document_type_id=i.documet_type_id\r\n"
  			+ "join group_tbl g on g.group_id=i.group_id\r\n"
  			+ "join files f on f.items_item_id=i.item_id\r\n"
  			+ " where i.parent_id=:id group by i.item_id   ",nativeQuery = true)
  	List<Map<String,Object>> itemsByNativeSQL(@Param("id") Integer id);
  	
  	
  	
  	
  	
}
