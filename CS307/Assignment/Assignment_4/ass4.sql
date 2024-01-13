create table if not exists player
(
    id bigint primary key,
    nickname text not null,
    country varchar(20),
    createdAt date,
    changeCount INT DEFAULT 0
);


create table if not exists restricted_words
(
    id serial primary key,
    word varchar(255) not null,
    country varchar(20) not null
);


create or replace function nickname_check()
    returns trigger
as
$$
    declare
    id_valid bool := true;
    nickname_valid bool := true;
    c varchar := null;
    x varchar := null;
begin
    -- constraint 1
    if (new.id < 10000000 or new.id >= 40000000) then
        id_valid := false;
    elsif (new.id >= 10000000 and new.id < 20000000) then
        if (new.country <> 'China') then
            id_valid := false;
        end if;
    elsif (new.id >= 20000000 and new.id < 30000000) then
        if (new.country <> 'America') then
            id_valid := false;
        end if;
    elsif (new.id >= 30000000 and new.id < 40000000) then
        if (new.country <> 'Other') then
            id_valid := false;
        end if;
    end if;

    -- constraint 2
    if (length(new.nickname) < 4 or length(new.nickname) > 20) then
        nickname_valid := false;
    end if;
    -- constraint 3
    for x in 1..length(new.nickname) loop
        c := substr(new.nickname, x, 1);
        if (c not in ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                     'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
                     'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                     'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                     'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7',
                     '8', '9', '_', '*', '#')) then
            nickname_valid := false;
        end if;
    end loop;

    -- constraint 5
    if (new.nickname in (select nickname from player)) then
        nickname_valid := false;
    end if;
    -- constraint 6
    for c, x in (select word, country from restricted_words) loop
        if (new.nickname like '%'||c||'%' and new.country = x) then
            nickname_valid := false;
        end if;
    end loop;
    -- constraint 7
    if (new.nickname like '%' || new.id || '%') then
        nickname_valid := false;
    end if;

    raise notice 'id_valid %', id_valid;
    raise notice 'nickname_valid %', nickname_valid;

    if (tg_op = 'INSERT') then
        if (id_valid and nickname_valid) then
            return new;
        end if;
    elsif (tg_op = 'UPDATE') then
        if (nickname_valid and new.changeCount < 3) then
            new.changeCount := new.changeCount + 1;
            return new;
        end if;
    end if;
    return null;
end
$$ language plpgsql;


create trigger nickname_trigger
    before insert or update
    on player
    for each row
execute procedure nickname_check();


-- test
insert into player values (10000001, 'abcd100000', 'China');
insert into player values (10000002, 'abcdcnm', 'China');
update player set nickname = 'h' where id = 10000001;
insert into restricted_words(word,country) values('cnm','China');
insert into restricted_words(word,country) values('fuck','America');
insert into restricted_words(word,country) values('restricted','Other');

-- truncate table
truncate table player;
