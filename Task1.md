# GiftFactory
Exam Chellange

Santa's Gift Factory  🎁 
Christmas is just around the corner, so Santa Claus and his little helpers (the elves) are in a 
hurry, to prepare all the gifts for the kids. Your task is to create a software program that 
supervises the operations of Santa's Gift Factory. 
OVERVIEW 
In the Factory there work many elves and the one and only Santa Claus. 
The elves' main task is to wrap the gifts and then pass it to Santa. Santa approves the gifts by 
sprinkling some magic dust on them and then he passes back them to the elves, so they can 
finally put them in the bottomless sack.  
So, what exact steps should a gift get through before it lands in Santa's sack? 
 first it arrives to an elf 
 the elf wraps it in some wrapping paper 
 the elf gives it to Santa 
 Santa sprinkles magic dust on it 
 Santa passes it back to the same elf he got it from 
 finally, the elf can put the gift in the sack 
An elf collects the unwrapped gifts until he gets an order to wrap them all at once. 
All the elves keep collecting unwrapped gifts and hold onto the wrapped gifts too, until a 
common command tells them that they can pass the wrapped gifts to Santa. 
Santa also waits for a command when he approves all the gifts in his room by sprinkling magic 
dust on them and sends them back immediately to the elves. Each gift should return to the elf 
who wrapped it. 
In this tough year there are only two types of gifts: toys and books. 
The toys have a name, and a type: they can be either a plush animal or a board game. 
The books have a name (their title) and the number of pages. 
All the gifts have a recommended age, so Santa knows which child would like them the most. 
TASK 1: IMPLEMENTING THE CLASS STRUCTURE & HIERARCHY 
You need to implement the needed class structure for the components of Santa's Gift Factory. 
An Elf - has a name and one list of all the stored gifts (unwrapped, wrapped and approved 
(covered with magic dust) gifts should be in the same list).  
 able to wrap gifts, to pass gifts to Santa and to put the approved gifts into the 
bottomless sack 
 the name will be a string, which could consist of any ASCII character 
 the stored gifts can be many and can be both toys and books 
 upon receiving the "wrapGifts" command, he wraps all stored unwrapped gifts 
 upon receiving the "passToSanta" command, he passes the wrapped gifts to Santa (Be 
careful! Avoid passing him gifts that were already approved, because he will be angry!) 
 upon receiving the "fillTheSack" command, all approved gifts are put in the sack and 
removed from the elves' lists 
(the sack doesn't have to be a separate class) 
Santa Claus – has one Map that stores the wrapped gifts and the corresponding elves 
 has functionality for approve (by sprinkling magic dust on) the gifts 
 the stored gifts can be many and can be both toys and books 
 upon receiving the "approveGifts" command, he covers all the gifts stored in his room 
with magic dust and then the gifts are returned to the elf who wrapped them 
The Gifts: - Toy – has a name, a type, a recommended age and two statuses that show if it is wrapped and 
if it is approved 
 the name is a string, which can consist of any ASCII character 
 the type can be plush animal or board game 
 the recommended age is an integer (should be between 0 and 18 (inclusive)) 
 by default, it is unwrapped and unapproved in the beginning - Book – has a name (its title), number of pages, a recommended age and two statuses that 
show if it is wrapped and if it is approved 
 the name is a string, which can consist of any ASCII character 
 the number of pages is an integer (should be between 4 and 10000 (inclusive)) 
 the recommended age is an integer (should be between 0 and 18 (inclusive)) 
 by default, it is unwrapped and unapproved in the beginning

