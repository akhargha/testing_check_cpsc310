package edu.trinity.got;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryMemberDAO implements MemberDAO {
    private final Collection<Member> allMembers =
            MemberDB.getInstance().getAllMembers();

    @Override
    public Optional<Member> findById(Long id) {
        return allMembers.stream()
                .filter(member -> member.id().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return allMembers.stream()
                .filter(member -> member.name().equals(name))
                .findFirst();
    }

    @Override
    public List<Member> findAllByHouse(House house) {
        return allMembers.stream()
                .filter(member -> member.house() == house)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Member> getAll() {
        return allMembers;
    }

    /**
     * Find all members whose name starts with S and sort by id (natural sort)
     */
    @Override
    public List<Member> startWithSandSortAlphabetically() {
        return allMembers.stream()
                .filter(member -> member.name().startsWith("S"))
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Final all Lannisters and sort them by name
     */
    @Override
    public List<Member> lannisters_alphabeticallyByName() {
        return allMembers.stream()
                .filter(member -> member.house().equals(House.LANNISTER))
                .sorted(Comparator.comparing(Member::name))
                .collect(Collectors.toList());
    }

    /**
     * Find all members whose salary is less than the given value and sort by house
     */
    @Override
    public List<Member> salaryLessThanAndSortByHouse(double max) {
        return allMembers.stream()
                .filter(member -> member.salary() < max)
                .sorted(Comparator.comparing(Member::house))
                .collect(Collectors.toList());
    }

    /**
     * Sort members by House, then by name
     */
    @Override
    public List<Member> sortByHouseNameThenSortByNameDesc() {
        return allMembers.stream()
                .sorted(Comparator.comparing(Member::house))
                .sorted(Comparator.comparing(Member::name))
                .collect(Collectors.toList());
    }

    /**
     * Sort the members of a given House by birthdate
     */
    @Override
    public List<Member> houseByDob(House house) {
        return allMembers.stream()
                .filter(member -> member.house() == house)
                .sorted(Comparator.comparing(Member::dob))
                .collect(Collectors.toList());
    }

    /**
     * Find all Kings and sort by name in descending order
     */
    @Override
    public List<Member> kingsByNameDesc() {
        return allMembers.stream()
                .filter(member -> member.title() == Title.KING)
                .sorted((n1, n2) -> n2.name().compareTo(n1.name()))
                .collect(Collectors.toList());
    }

    /**
     * Find the average salary of all the members
     */
    @Override
    public double averageSalary() {
        return allMembers.stream()
                .mapToDouble(Member::salary)
                .average()
                .orElse(0.0);
    }

    /**
     * Get the names of a given house, sorted in natural order
     * (note sort by _names_, not members)
     */
    @Override
    public List<String> namesSorted(House house) {
        return allMembers.stream()
                .filter(member -> member.house() == house)
                .map(Member::name)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Are any of the salaries greater than 100K?
     */
    @Override
    public boolean salariesGreaterThan(double max) {
        return allMembers.stream()
                .anyMatch(member -> member.salary() > max);
    }

    /**
     * Are there any members of given house?
     */
    @Override
    public boolean anyMembers(House house) {
        return false;
    }

    /**
     * How many members of a given house are there?
     */
    @Override
    public long howMany(House house) {
        return 0;
    }

    /**
     * Return the names of a given house as a comma-separated string
     */
    @Override
    public String houseMemberNames(House house) {
        return "";
    }

    /**
     * Who has the highest salary?
     */
    @Override
    public Optional<Member> highestSalary() {
        return Optional.empty();
    }

    /**
     * Partition members into royalty and non-royalty
     * (note: royalty are KINGs and QUEENs only)
     */
    @Override
    public Map<Boolean, List<Member>> royaltyPartition() {
        return Collections.emptyMap();
    }

    /**
     * Group members into Houses
     */
    @Override
    public Map<House, List<Member>> membersByHouse() {
        return Collections.emptyMap();
    }

    /**
     * How many members are in each house?
     * (group by house, downstream collector using counting
     */
    @Override
    public Map<House, Long> numberOfMembersByHouse() {
        return Collections.emptyMap();
    }

    /**
     * Get the max, min, and ave salary for each house
     */
    @Override
    public Map<House, DoubleSummaryStatistics> houseStats() {
        return Collections.emptyMap();
    }

}
