import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read N, M, K
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        // Initialize lists to store the team and institution information
        List<Team> teams = new ArrayList<>();
        Set<String> topMInstitutions = new HashSet<>();
        Map<String, Team> bestTeamsPerInstitution = new HashMap<>();

        // Read the team names and their institutions
        for (int i = 0; i < N; i++) {
            String teamName = scanner.next();
            String institutionName = scanner.next();
            teams.add(new Team(i + 1, teamName, institutionName));
        }

        // Determine the institutions in the top M
        for (int i = 0; i < M; i++) {
            topMInstitutions.add(teams.get(i).institution);
        }

        // Find the best team from each institution that is not in the top M
        for (int i = M; i < N; i++) {
            Team team = teams.get(i);
            if (!topMInstitutions.contains(team.institution)) {
                if (!bestTeamsPerInstitution.containsKey(team.institution)) {
                    bestTeamsPerInstitution.put(team.institution, team);
                }
            }
        }

        // Sort the best teams based on their original rank
        List<Team> goldenTicketTeams = new ArrayList<>(bestTeamsPerInstitution.values());
        Collections.sort(goldenTicketTeams, Comparator.comparingInt(t -> t.rank));

        // Limit the number of Golden Tickets to K
        goldenTicketTeams = goldenTicketTeams.subList(0, Math.min(K, goldenTicketTeams.size()));

        // Output the result
        System.out.println(goldenTicketTeams.size());
        for (Team team : goldenTicketTeams) {
            System.out.println(team.name);
        }
    }

    static class Team {
        int rank;
        String name;
        String institution;

        Team(int rank, String name, String institution) {
            this.rank = rank;
            this.name = name;
            this.institution = institution;
        }
    }
}
