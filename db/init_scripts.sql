
insert into user_tl (id, first_name, last_name, user_name, password, email, created_by, created_time, is_active)
values (1, 'admin', 'admin', 'admin', 'test', 'admin@mtg.com', 1, NOW(), true);

insert into user_tl (id, first_name, last_name, user_name, password, email, created_by, created_time, is_active)
values (2,'user', 'user', 'user', 'test', 'user@mtg.com', 1, NOW(), true);

insert into role_tl (id, title, description, created_by, created_time)
values (1, 'ADMIN', 'Admin Role access to admin panel', 1,now());

insert into role_tl (id, title, description, created_by, created_time)
values (2, 'USER', 'User role', 1,now());

insert into user_role values(1,1);
insert into user_role values(1,2);
insert into user_role values(2,2);

-- delete from menu_link;

insert into menu_link_tl (id, icon_name, icon_type, label, router_link, toggle, parent_menu_id, is_visible, created_by, created_time)
values (1, 'shield', 'fi', 'Dashboard', '/dashboard', null, null, true, 1,now());


-- delete from reilize.user_menu_link;

insert into role_menu_link (role_id, menu_link_id) values (1, 1);

insert into role_menu_link (role_id, menu_link_id) values (2, 1);