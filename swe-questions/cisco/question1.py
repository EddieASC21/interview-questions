class question1:
    def backspaceCompare(self, s: str, t: str) -> int:
        def stack_to_compare(text):
            stack = []
            for c in text:
                if c == '#':
                    if stack:
                        stack.pop()
                else:
                    stack.append(c)
            return stack

        return 1 if stack_to_compare(s) == stack_to_compare(t) else 0


# test cases by chat
if __name__ == "__main__":
    q = question1()

    print(q.backspaceCompare("ab#c", "ad#c"))     # 1 → both become "ac"
    print(q.backspaceCompare("ab##", "c#d#"))     # 1 → both become ""
    print(q.backspaceCompare("a#c", "b"))         # 0 → "c" vs "b"
    print(q.backspaceCompare("a##c", "#a#c"))     # 1 → both become "c"
    print(q.backspaceCompare("a#b#c#", ""))       # 1 → all deleted
    print(q.backspaceCompare("", ""))             # 1 → both empty
    print(q.backspaceCompare("abc#d", "acc#c"))   # 0 → "abd" vs "ac"
    print(q.backspaceCompare("bxj##tw", "bxo#j##tw")) # 1 → both become "tw"
    print(q.backspaceCompare("nzp#o#g", "b#nzp#o#g")) # 1 → both become "nog"
    print(q.backspaceCompare("a###", ""))         # 1 → all characters removed
