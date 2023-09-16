def count_groups(test_cases):
    # I don't think this answer is correct. I just wrote this and submitted without testing in hackerrank just 2 second before time end.
    # Consider a social networking service consisting of Nusers. For creating an account the
    # user needs a username but the service does not check the uniqueness of the username.
    # Thus, any user can have any username. Also, a user can create multiple accounts and
    # these accounts may also share the same username. You are assigned a task to group users
    # sharing at least one common username.
    #
    # Task
    # The task is to determine the count of such groups of users such that two different
    # groups do not have any users sharing a common username.
    #
    #
    # Input
    # First line contains an integer T denoting the number of test cases. For each test case:
    # The first line contains a number N denoting the number of users.
    # Next, the N line contains an integer M denoting the number of accounts a user has and M space- separated strings in
    # upper case representing the username.
    #
    #
    # Sample Input
    # 1
    # 3
    # 2 ALICE BOB
    # 2 ALEX ALEX
    # 1 ALICE
    #
    # Sample Output
    # 2
    #
    # Explanation
    # The first and third users share a common usernames ['ALICE']. Hence they form a group.
    # The second user does not share a username with anyone. Hence, he forms another group.
    # Hence, there are 2 groups in total.

    for users in test_cases:
        groups = {}  # A dictionary to store groups

        for i in range(users):
            usernames = i.split()[1:]
            new_group = True

            # Check if the current user shares a username with any existing group
            for username in usernames:
                if username in groups:
                    # If the user shares a username with an existing group, add them to that group
                    group_id = groups[username]
                    new_group = False
                    break

            if new_group:
                # If the user doesn't share a username with any existing group, create a new group
                group_id = len(groups) + 1

            # Update the groups dictionary with the user's usernames
            for username in usernames:
                groups[username] = group_id

        # Count the number of unique group IDs to get the total number of groups
        unique_groups = set(groups.values())
        print(len(unique_groups))


# Input processing
T = int(input())
test_cases = []
for _ in range(T):
    N = int(input())
    for _ in range(N):
        test_cases.append(input())
    count_groups(test_cases)


count_groups(test_cases)
#
# if __name__ == '__main__':
#     T = int(input())
#     test_cases = []
#     for _ in range(T):
#         N = int(input())
#         for _ in range(N):
#             test_cases.append(input())
#         test_cases.append(N)




# 2ns question was knapsack. 


 
def knapSack(W, wt, val, n):
 
    # Base Case
    if n == 0 or W == 0:
        return 0

    if (wt[n-1] > W):
        return knapSack(W, wt, val, n-1)

    else:
        return max(
            val[n-1] + knapSack(
                W-wt[n-1], wt, val, n-1),
            knapSack(W, wt, val, n-1))
 

 
# Driver Code
if __name__ == '__main__':
    profit = [60, 100, 120]
    weight = [10, 20, 30]
    W = 50
    n = len(profit)
    print knapSack(W, weight, profit, n)
 
