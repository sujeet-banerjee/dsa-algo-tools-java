# temp-reporting-tool
For Temporary purpose.

Algo - Two Approaches:

Approach 1
----------
Maintain enttries into a HashMap (which is assorted), which allows O(1) insertion, update and deletion. However, on every reporting, one has to Sort the entries based on the aggregated consumption, which is O(n . lg(n)) each time. Thus is not a good choice if the updates are not very frequent, but the reporting is required very often. Implementation: using Java HashMap as the main map and Collections.sort(), with a Comparator, for reporting.

Add/Update - O(1)
Reporting - O(n. lg(n) )


Approach 2
----------
Maintain sorted collection of Entries, based on the metric (in this case the reverse order by the aggregate resource consumption). This reuires O(n) computations while reporting and is thus optimized for very frequent reporting, compared to the Approach 1. The Java TreeSet / TreeMap ensures Binary search Tree guaranties, using the Red-Black tree implementation. However, the challange is to maintain the order based on a non-key attribute. The Java SortedMap/SortecSet datastructure do not allow ordering based on non-key attribute (i.e. aggregated resource consumption), and that's don RIGHTLY so - to ensure O(lg n) guarantees on searching/retrieval/deletion. I's thus imperative to maintain anoher Map (HashMap for O(1) update/insert/delete) so as to maintain updates to the entries based on the natural key (in this case the Label-name). To come to this solution, I did study Java's Tree based implementation, and also this JIRA ticket: https://bugs.openjdk.org/browse/JDK-6770446 

Add/Update - O(lg n)
Reporting - O(n)
