print('dump users start');

db.users.insertMany([
		{username: "adminuser", password: "$2a$10$Mkf4nJx2XClf7dMgzhRqFu6zHrn65Igr0OQg9m3qWV5tC3fCUkZs.",
		    email: "adminuser@dminc.com", accountNonExpired: true, accountNonLocked: true, credentialsNonExpired: true,
		    enabled: true
		},
		{username: "regular", password: "$2a$10$Mkf4nJx2XClf7dMgzhRqFu6zHrn65Igr0OQg9m3qWV5tC3fCUkZs.",
		    email: "regular@dminc.com", accountNonExpired: true, accountNonLocked: true, credentialsNonExpired: true,
		    enabled: true
		}
	]
);


print('dump users complete');