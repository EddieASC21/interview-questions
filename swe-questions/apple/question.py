from collections import defaultdict
from typing import List, Dict

class Solution:
    def __init__(self):
        self.updated_routes: Dict[str, List[List[str]]] = defaultdict(list)

    def solution(self, routes: Dict[str, List[str]]) -> Dict[str, List[List[str]]]:
        for model, path in routes.items():
            if not self.updated_routes[model]:
                self.updated_routes[model].append(path[:])
            else:
                self.helper(model, path)
        return self.updated_routes

    def helper(self, model: str, curr: List[str]):
        new_routes = []
        loc = curr[0]
        next_stop = curr[-1]

        for route in self.updated_routes[model]:
            dest = route[-1]
            if dest == loc:
                route.append(next_stop)
                break
        else:
            new_routes.append(curr[:])

        self.updated_routes[model].extend(new_routes)


# Test cases by chatgpt
if __name__ == "__main__":
    s = Solution()

    input1 = {
        "iphone 13": ["USA", "BRAZIL"],
        "iphone 14": ["USA", "SPAIN"],
        "iphone 15": ["SPAIN", "BRAZIL"]
    }

    input2 = {"iphone 14": ["SPAIN", "BRAZIL"]}
    input3 = {"iphone 13": ["ENGLAND", "BRAZIL"]}
    input4 = {"iphone 15": ["USA", "ENGLAND"]}

    s.solution(input1)
    for inp in [input2, input3, input4]:
        for model in inp:
            s.helper(model, inp[model])

    for model, chains in s.updated_routes.items():
        print(f"{model}: {chains}")
