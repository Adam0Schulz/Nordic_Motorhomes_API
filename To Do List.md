# To Do List

- rethink **status system in database** - instead of having tables for the states having table for all the states and just connecting it to the bookings - ***done***
- rethink **service layer** - clean up the code - ***done***
- redo the **motorhome availability** in service layer to avoid **dependency cycle** - ***done***
- rethink motorhome status because it maybe doesn't make sense.  On a second thought it maybe does but still how does it work when the repairs take longer or something like that
- don't forget to check if jsonignore annotation works like you expected
- check if basic crud functionality of services could be abstracted to interface or something like that
- do something about percentage calculation in calcTotal of booking
- somehow solve drop off and cancel dependency on right status id to be passed in as param

# Things that I changed compared to the original

- instead of having a table for each status of booking and motorhome (motorhomesToCheck, ActiveBooking, ...) which forced the system to have not modifiable statuses. I changed it there's only one table of every status and bookings/motorhomes reference it with foreign key.
- 