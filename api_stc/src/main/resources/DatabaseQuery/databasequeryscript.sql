

select u.user_id,u.user_name,group_concat(t.training_id)as training_lesson ,count(*) as count_of_Lesson,t.training_date
from user u
join Training_details t on u.user_id=t.user_id
group by user_id,t.training_date having count_of_Lesson >1 
order by t.training_date desc